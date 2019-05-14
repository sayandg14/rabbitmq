/**
 * 
 */
package com.example.rabbitmqfiledemo.pojo;

import java.io.Serializable;

/**
 * @author user
 *
 */
public class SimpleMessage implements Serializable {

	private static final long serialVersionUID = 8743259644761933984L;
	private String name;
	private String description;
	
	
	public SimpleMessage() {
	}
	
	@Override
	public String toString() {
		return "SimpleMessage [name=" + name + ", description=" + description + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
