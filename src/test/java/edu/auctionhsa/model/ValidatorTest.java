package edu.auctionhsa.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 
 * These unit tests are use to verify the mandatory contraints of the entities.
 * 
 * @author Jerson Viveros
 *
 */

public class ValidatorTest {
	
	private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }
	
	@Test
    public void validateUserEntity() {
        User user = new User();
        user.setName("Jerson Viveros");
        user.setNumId("morethan64letterssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss	");

        Validator validator = createValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.size()).isEqualTo(2);
        ConstraintViolation<User> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isIn("usr", "numId");
    }
	
	@Test
    public void validateBidEntity() {
	    Bid bid = new Bid();
		
        Validator validator = createValidator();
        Set<ConstraintViolation<Bid>> constraintViolations = validator.validate(bid);

        assertThat(constraintViolations.size()).isEqualTo(2);
        ConstraintViolation<Bid> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isIn("amount", "item");
    }
	
	@Test
    public void validateItemEntity() {
	    Item item = new Item();
		
        Validator validator = createValidator();
        Set<ConstraintViolation<Item>> constraintViolations = validator.validate(item);

        assertThat(constraintViolations.size()).isEqualTo(3);
        ConstraintViolation<Item> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isIn("name", "auctionEnd", "seller");
    }

}
