package org.jboss.maven.plugins.bombuilder;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

public class OrderedPropertiesTest {

	@Test
	public void testPropertiesAreNotSortedInOrderOfAdding() throws Exception {
		Properties properties = new Properties();
		properties.put("project.build.sourceEncoding", "utf-8");
		properties.put("version.org.codehaus.plexus", "1.2.3");

		assertEquals("version.org.codehaus.plexus", properties.keySet().iterator().next());
	}

	@Test
	public void testOrderedPropertiesAreSortedInOrderOfAdding() throws Exception {
		OrderedProperties properties = new OrderedProperties();
		properties.put("project.build.sourceEncoding", "utf-8");
		properties.put("version.org.codehaus.plexus", "1.2.3");

		assertEquals("project.build.sourceEncoding", properties.keySet().iterator().next());
	}

	@Test
	public void testPutAll() throws Exception {
		Properties properties = new Properties();
		properties.put("project.build.sourceEncoding", "utf-8");

		OrderedProperties orderedProperties = new OrderedProperties();
		orderedProperties.putAll(properties);

		assertEquals(properties.size(), orderedProperties.keySet().size());
	}
}