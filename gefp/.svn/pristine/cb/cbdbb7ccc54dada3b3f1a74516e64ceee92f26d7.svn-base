package gefpmvc.web.validator;

import gefpmvc.model.User;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom( clazz );
	}

	@Override
	public void validate(Object target, Errors errors) {
		 User user = (User) target;

	        if( !StringUtils.hasText( user.getUsername() ) )
	            errors.rejectValue( "username", "error.username.required" );

	        if( !StringUtils.hasText( user.getPassword() ) ){
	            errors.rejectValue( "password", "error.password.required" );
	        }/*else   if( user.getPassword().length() <8)
	            errors.rejectValue( "password", "error.password.short" );*/
	        else    if( StringUtils.startsWithIgnoreCase(user.getPassword(), "admin")){
	        	String[] objReturn = {user.getUsername()};//;
	        	errors.rejectValue( "password", "error.username.taken",objReturn  ,"Contact Administrator");
	        }
	            
	        
	}

}
