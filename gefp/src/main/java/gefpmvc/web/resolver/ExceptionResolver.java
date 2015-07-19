package gefpmvc.web.resolver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;



public class ExceptionResolver extends SimpleMappingExceptionResolver {

	 private static Logger logger = LoggerFactory.getLogger( ExceptionResolver.class );

	 
    @Override
    public ModelAndView resolveException( HttpServletRequest request,
        HttpServletResponse response, Object handler, Exception exception )
    {
    	
    	System.out.println(" misha chandan/... logger");
        if( !exception.getClass().getName().endsWith( "AccessDeniedException" ) )
        {		/*((User)request.getSession().getAttribute(Constants.AUTHENTICATEDUSER)).getUsername()*/
        		System.out.println("Exception caused by "+"\n"+ exception);
          /*  logger.error( "Exception caused by " + ((User)request.getSession().getAttribute(Constants.AUTHENTICATEDUSER)).getUsername(), exception );*/
        }
        logger.error( "Exception caused by " , exception );

        return super.resolveException( request, response, handler, exception );
    }

}
