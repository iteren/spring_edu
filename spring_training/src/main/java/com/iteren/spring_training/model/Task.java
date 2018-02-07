package com.iteren.spring_training.model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task {

	@Id
	@NotNull
	@Min(value = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 2, max = 50, message = "Wrong task name, name should be at least 4 characters and 15 characters maximum!")
	private String name;

	@NotNull
	@Size(min = 5, max = 255, message = "Wrong task description, description should be at least 5 characters and 255 characters maximum!")
	private String description;

	@NotNull
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Date dueDate;

	private boolean compleated;
}

class CustomDateSerializer extends StdSerializer<Date> {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public CustomDateSerializer() {
		this(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomDateSerializer(Class clazz) {
		super(clazz);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		gen.writeString(formatter.format(value));
	}
}

class CustomDateDeserializer extends StdDeserializer<Date> {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter = 
      new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
 
    public CustomDateDeserializer() {
        this(null);
    }
 
    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }
 
    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException, JsonProcessingException {
        String date = jsonparser.getText();
        try {
			return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
