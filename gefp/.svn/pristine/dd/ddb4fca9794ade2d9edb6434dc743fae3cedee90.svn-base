package gefpmvc.web.validator;

import gefpmvc.model.UI.UserUIModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserUIValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserUIModel.class.isAssignableFrom( clazz );
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserUIModel user = (UserUIModel) target;

        String PASSWORD_PATTERN =  "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,})"; /*(?=.*[@#$%])*/
		Pattern  pattern = Pattern.compile(PASSWORD_PATTERN);
		
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		//String EMAIL_PATTERN = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
		//String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";	
		Pattern  emailpattern = Pattern.compile(EMAIL_PATTERN);
		
		
        if( !StringUtils.hasText( user.getPassword() ) && !StringUtils.hasText( user.getConfirmPassword() )  ){
           // errors.rejectValue( "password", "error.password.required" );
        	user.setPassword("");
        	user.setConfirmPassword("");
        }else{
        	if( StringUtils.startsWithIgnoreCase(user.getPassword(), "admin")){
            	String[] objReturn = {user.getPassword()};
            	errors.rejectValue( "password", "error.password.taken",objReturn  ,"Contact Administrator");
            }else {
            		Matcher  matcher = pattern.matcher(user.getPassword());
            		
            		if( !matcher.matches()){
            			errors.rejectValue( "password", "error.password.strong" );
            		}
    	            
            }
        	if( StringUtils.startsWithIgnoreCase(user.getConfirmPassword(), "admin")){
            	String[] objReturn = {user.getConfirmPassword()};//;
            	errors.rejectValue( "confirmPassword", "error.confirmPassword.taken",objReturn ,"Contact Administrator");
            }else {
            	Matcher  matcher = pattern.matcher(user.getConfirmPassword());
        		
        		if( !matcher.matches()){
        			errors.rejectValue( "confirmPassword", "error.confirmPassword.strong" );
        		}
            	
            }
                   
            if( !user.getPassword().equals(user.getConfirmPassword()) ){
                errors.rejectValue( "password", "error.password.same" );
            }  
        }
        	
        
        if( !StringUtils.hasText( user.getConfirmPassword() ) ){
           // errors.rejectValue( "confirmPassword", "error.confirmPassword.required" );
        }
        
        if( !StringUtils.hasText( user.getFname() ) ){
            errors.rejectValue( "fname", "error.fname.required" );
        }
        
        if( !StringUtils.hasText( user.getLname()) ){
            errors.rejectValue( "lname", "error.lname.required" );
        }
        
        if( !StringUtils.hasText( user.getEmailid()) ){
            errors.rejectValue( "emailid", "error.emailid.required" );
        }else{
        	Matcher  matcherEmail = emailpattern.matcher(user.getEmailid());
        	if(!matcherEmail.matches()){
        		errors.rejectValue( "emailid", "error.emailid.format" );
        	}
        	
        }
        
        if( !StringUtils.hasText( user.getAddress()) ){
            errors.rejectValue( "address", "error.address.required" );
        }

/*	        String PASSWORD_PATTERN =  "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{4,})";
			Pattern  pattern = Pattern.compile(PASSWORD_PATTERN);
			
			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern  emailpattern = Pattern.compile(EMAIL_PATTERN);
			
	        if( !StringUtils.hasText( user.getPassword() ) ){
	            errors.rejectValue( "password", "error.password.required" );
	        }else if( StringUtils.startsWithIgnoreCase(user.getPassword(), "admin")){
	        	String[] objReturn = {user.getPassword()};
	        	errors.rejectValue( "password", "error.password.taken",objReturn  ,"Contact Administrator");
	        }else {
	        		Matcher  matcher = pattern.matcher(user.getPassword());
	        		
	        		if( !matcher.matches()){
	        			errors.rejectValue( "password", "error.password.strong" );
	        		}
		            
	        }
	        
	        if( !StringUtils.hasText( user.getConfirmPassword() ) ){
	            errors.rejectValue( "confirmPassword", "error.confirmPassword.required" );
	        }else  if( StringUtils.startsWithIgnoreCase(user.getConfirmPassword(), "admin")){
	        	String[] objReturn = {user.getConfirmPassword()};//;
	        	errors.rejectValue( "confirmPassword", "error.confirmPassword.taken",objReturn ,"Contact Administrator");
	        }else {
	        	Matcher  matcher = pattern.matcher(user.getConfirmPassword());
        		
        		if( !matcher.matches()){
        			errors.rejectValue( "confirmPassword", "error.confirmPassword.strong" );
        		}
	        	
	        }
	               
	        if( !user.getPassword().equals(user.getConfirmPassword()) ){
	            errors.rejectValue( "password", "error.password.same" );
	        }  
	        
	        if( !StringUtils.hasText( user.getFname() ) ){
	            errors.rejectValue( "fname", "error.fname.required" );
	        }
	        
	        if( !StringUtils.hasText( user.getLname()) ){
	            errors.rejectValue( "lname", "error.lname.required" );
	        }
	        
	        if( !StringUtils.hasText( user.getEmailid()) ){
	            errors.rejectValue( "emailid", "error.emailid.required" );
	        }else{
	        	Matcher  matcherEmail = emailpattern.matcher(user.getConfirmPassword());
	        	if(!matcherEmail.matches()){
	        		errors.rejectValue( "emailid", "error.emailid.format" );
	        	}
	        	
	        }
	        
	        if( !StringUtils.hasText( user.getAddress()) ){
	            errors.rejectValue( "address", "error.address.required" );
	        }
*/	        
	}

}
