package app.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;

@Stateless
public class Observable {
	
	final static Logger logger = Logger.getLogger(Observable.class);
	
	public void listenForCarAddedEvent(@Observes CarAddedEvent event) {
	    logger.info("OBSERVABLE SAYS: " + event.getCar());
	}
}
