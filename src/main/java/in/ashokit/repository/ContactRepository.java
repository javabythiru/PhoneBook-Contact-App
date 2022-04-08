package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.Contact;

@Repository  // Here adding this annotation is optional. By default this will be scanned.
public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
