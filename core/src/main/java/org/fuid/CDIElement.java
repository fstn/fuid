package org.fuid;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Interface qui me permet de mettre en place tous les elements neccessaires  CDI
 * @author stephen
 *
 */
public interface CDIElement extends Serializable {
 @PostConstruct
 public void construct();
 @PreDestroy
 public void destroy();
 
 
}
