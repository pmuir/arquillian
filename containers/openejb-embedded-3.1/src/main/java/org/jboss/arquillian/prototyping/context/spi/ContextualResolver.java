/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.prototyping.context.spi;

import java.util.Map;

import org.jboss.arquillian.prototyping.context.api.Properties;

/**
 * An entity capable of resolving a given type and
 * optional contextual properties to an implementation
 * instance.  In practice this may be used by Arquillian or
 * backing containers to supply resources such as EJB or 
 * JNDI Naming Context references.  The contextual properties
 * consulted will vary per implementation, and should be used
 * when pure type-based resolution techniques are insufficient
 * or non-deterministic. 
 * 
 * @author <a href="mailto:andrew.rubinger@jboss.org">ALR</a>
 * @version $Revision: $
 */
public interface ContextualResolver
{
   //-------------------------------------------------------------------------------------||
   // Contracts --------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||

   /**
    * Obtains an instance of the requested type from Arquillian
    * or the underlying target container.  To provide additional 
    * context, see {@link ContextualResolver#resolve(Class, Map)}.
    * 
    * @return An instance of the type requested, or null if none is supported
    *   by the container for the given arguments
    * @param type The type of object to be returned from the container
    * @throws IllegalArgumentException If the type if not specified
    */
   <T> T resolve(Class<T> type) throws IllegalArgumentException;

   /**
    * Obtains an instance of the requested type from Arquillian
    * or the underlying target container.  The supplied properties
    * may be used to define additional context used to resolve
    * the correct instance to be returned: for instance @EJB injection
    * by type may also require a beanName to be deterministic. 
    * 
    * @return An instance of the type requested, or null if none is supported
    *   by the container for the given arguments
    * @param type The type of object to be returned from the container
    * @param properties Additional context used to determine object resolution.
    *   The keys and values contained herein may be container-specific
    * @throws IllegalArgumentException If either argument is not specified
    */
   <T> T resolve(Class<T> type, Map<String, Object> properties) throws IllegalArgumentException;

   /**
    * Obtains an instance of the requested type from Arquillian
    * or the underlying target container.  The supplied properties
    * may be used to define additional context used to resolve
    * the correct instance to be returned: for instance @EJB injection
    * by type may also require a beanName to be deterministic.  This method is 
    * functionally equivalent to {@link ContextualResolver#resolve(Class, Map)}
    * where the supplied properties are converted to a {@link Map} view.
    * 
    * @return An instance of the type requested, or null if none is supported
    *   by the container for the given arguments
    * @param type The type of object to be returned from the container
    * @param properties Additional context used to determine object resolution.
    *   The keys and values contained herein may be container-specific
    * @throws IllegalArgumentException If either argument is not specified
    */
   <T> T resolve(Class<T> type, Properties properties) throws IllegalArgumentException;

}
