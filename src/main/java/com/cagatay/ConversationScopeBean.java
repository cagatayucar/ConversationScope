package com.cagatay;


import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class ConversationScopeBean implements Serializable
{
	private Employee employee;

	public Employee getEmployee()
	{
		return employee;
	}

	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}

	public ConversationScopeBean(){
		employee = new Employee();

	}
	@Inject
	Conversation conversation;

	public Conversation getConversation()
	{
		return conversation;
	}

	public void beginConversation(){
		if (conversation.isTransient()){
			conversation.begin();
		}
	}
	public void endConversation(){
		if (!conversation.isTransient()){
			conversation.end();
		}
	}
	public String start(){
		beginConversation();
		return "2loginConversationScoped?faces-redirect=true";
	}
	public String finish(){
		endConversation();
		return "6afterConversationScoped?faces-redirect=true";
	}
}
