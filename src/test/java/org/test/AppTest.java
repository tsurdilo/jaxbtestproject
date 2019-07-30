package org.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;

import org.junit.Test;

import org.kie.server.api.model.instance.ProcessInstance;
import org.xml.sax.InputSource;

public class AppTest {

public static String TEST_XML = "<process-instance>" +
"    <process-instance-id>1</process-instance-id>" +
"    <process-id>com.myspace.myproject.testprocess</process-id>" +
"    <process-name>testprocess</process-name>" +
"    <process-version>1.0</process-version>" +
"    <process-instance-state>1</process-instance-state>" +
"    <container-id>myproject_1.0.0-SNAPSHOT</container-id>" +
"    <initiator>wbadmin</initiator>" +
"    <start-date>2019-07-30T08:44:18.032-04:00</start-date>" +
"    <process-instance-desc>testprocess</process-instance-desc>" +
"    <correlation-key>1</correlation-key>" +
"    <parent-instance-id>-1</parent-instance-id>" +
"    <sla-compliance>0</sla-compliance>" +
"    <variables>" +
"        <entry>" +
"            <key>myvar1</key>" +
"            <value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">var1Value</value>" +
"        </entry>" +
"        <entry>" +
"            <key>initiator</key>" +
"            <value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">wbadmin</value>" +
"        </entry>" +
"        <entry>" +
"            <key>myvar2</key>" +
"            <value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">var2Value</value>" +
"        </entry>" +
"        <entry>" +
"            <key>myvar3</key>" +
"            <value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">var3Value</value>" +
"        </entry>" +
"    </variables>" +
"</process-instance>";


    @Test
    public void shouldAnswerWithTrue() throws Exception {
        JAXBContext context = JAXBContext.newInstance(ProcessInstance.class);
        InputSource inputSource = new InputSource( new StringReader( TEST_XML ) );
        ProcessInstance pi = (ProcessInstance) context.createUnmarshaller().unmarshal(inputSource);

        assertNotNull(pi);
        assertNotNull(pi.getVariables());
        assertEquals(4, pi.getVariables().size());
    }
}
