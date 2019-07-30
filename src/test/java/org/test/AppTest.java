package org.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

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

public static String RESULT_FROM_ROUNDTRIP = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><process-instance><process-instance-id>1</process-instance-id><variables><entry><key>myvar1</key><value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">var1Value</value></entry><entry><key>myvar2</key><value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">var1Value2</value></entry><entry><key>myvar3</key><value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">var1Value3</value></entry><entry><key>myvar4</key><value xsi:type=\"xs:string\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">var1Value4</value></entry></variables></process-instance>";


    @Test
    public void testUnmarshallFromXML() throws Exception {
        JAXBContext context = JAXBContext.newInstance(ProcessInstance.class);
        InputSource inputSource = new InputSource( new StringReader( TEST_XML ) );
        ProcessInstance pi = (ProcessInstance) context.createUnmarshaller().unmarshal(inputSource);

        assertNotNull(pi);
        assertNotNull(pi.getVariables());
        assertEquals(4, pi.getVariables().size());
    }

    @Test
    public void testRoundtrip() throws Exception {
        Map<String, Object> pvars = new HashMap<>();
        pvars.put("myvar1", "var1Value");
        pvars.put("myvar2", "var1Value2");
        pvars.put("myvar3", "var1Value3");
        pvars.put("myvar4", "var1Value4");


        ProcessInstance pi = new ProcessInstance();
        pi.setId(1L);
        pi.setVariables(pvars);

        JAXBContext context = JAXBContext.newInstance(ProcessInstance.class);
        Marshaller m = context.createMarshaller();
        StringWriter sw = new StringWriter();
        m.marshal(pi, sw);

        String result = sw.toString();

        assertNotNull(result);
        
        assertEquals(RESULT_FROM_ROUNDTRIP, result  );

        InputSource inputSource = new InputSource(new StringReader(result));
        ProcessInstance pi2 = (ProcessInstance) context.createUnmarshaller().unmarshal(inputSource);

        assertNotNull(pi2);
        assertNotNull(pi2.getVariables());
        assertEquals(4, pi2.getVariables().size());

    }
}
