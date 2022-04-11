package in.ashokit.rest;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.constants.AppConstants;
import in.ashokit.entity.Contact;
import in.ashokit.props.AppProperties;
import in.ashokit.service.ContactService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class ContactRestController {
	@Autowired
	private ContactService service;
	
	@Autowired
	private AppProperties appProps;
	
	Map<String, String> messages;
	
	@PostMapping("/contact")
	@ApiOperation("Adds a new Contact details to the table in the Database")
	public String saveContact(@RequestBody Contact contact) {
		boolean status=service.saveContact(contact);
		messages = appProps.getMessages();
			
		if(status) {
			//return "Contact Saved.....";
			return messages.get(AppConstants.CONTACT_SAVE_SUCCESS_MSG);			
		}else {
			//return "Failed to save Contact....";
			return messages.get(AppConstants.CONTACT_SAVE_FAIL_MSG);
		}
	}
	@GetMapping("/contacts")
	@ApiOperation("Returns all Contact Detais from the Database")
	public List<Contact> getAllContact(){
		return service.getAllContacts();
	}

	@GetMapping("/contact/{cid}")
	@ApiOperation("Returns specified Contact details from the Database based on given Contact ID")
	public Contact editContact(@PathVariable("cid")Integer contactId) {
		return service.getContactById(contactId);
	}
	@DeleteMapping("/contact/{cid}")
	@ApiOperation("Deletes the specified record temporarily from the Database based on given Contact ID")
	public String deleteContact(@PathVariable("cid")Integer contactId) {
		boolean status = service.deleteContactById(contactId);
		messages=appProps.getMessages();
		
		if(status) {
			//return "Contact Deleted.....";
			return messages.get(AppConstants.CONTACT_DEL_SUCCESS_MSG);
		}else {
			//return "Failed to Delete....";
			return messages.get(AppConstants.CONTACT_DEL_FAIL_MSG);
		}
	}

}
