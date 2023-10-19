package com.tander.xmlToCSVTransformer.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

@Getter
@Setter
@AllArgsConstructor
public class XMLCreator {
    private static final Logger logger = LoggerFactory.getLogger(XMLCreator.class);

    private String xmlFileName;
    private String xmlTransformFileName;
    private String templateXML;
    private String templateCSV;
    private String csvFileName;

    public boolean createXML(Object o) {
        logger.info("Create XML: start.");
        try (FileWriter fileWriter = new FileWriter(xmlFileName)) {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(o, writer);
            fileWriter.write(writer.getBuffer().toString());
            logger.info("Create XML: success.");
            return true;
        } catch (JAXBException e) {
            logger.error("Error while creating xml: ", e);
        } catch (IOException e) {
            logger.error("Error while working with file: ", e);
        }
        return false;
    }

    public boolean transformXML() {
        logger.info("Transform to XML: start.");
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Resource xsltResource = new ClassPathResource(templateXML);
            Source xslt = new StreamSource(xsltResource.getInputStream());
            Transformer transformer = factory.newTransformer(xslt);
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            Source xml = new StreamSource(new File(xmlFileName));
            transformer.transform(xml, new StreamResult(new File(xmlTransformFileName)));
            logger.info("Transform to XML: success.");
            return true;
        } catch (TransformerException e) {
            logger.error("Error while transforming xml: ", e);
        }
        catch (IOException e){
            logger.error("Error while reading file: ", e);
        }
        return false;
    }

    public boolean transformCSV() {
        logger.info("Transform to CSV: start.");
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Resource xsltResource = new ClassPathResource(templateCSV);
            Source xslt = new StreamSource(xsltResource.getInputStream());
            Transformer transformer = factory.newTransformer(xslt);
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            Source xml = new StreamSource(new File(xmlTransformFileName));
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(xml, new StreamResult(new File(csvFileName)));
            logger.info("Transform to CSV: success.");
            return true;
        } catch (TransformerException e) {
            logger.error("Error while transforming xml: ", e);
        }
        catch (IOException e){
            logger.error("Error while reading file: ", e);
        }
        return false;
    }
}
