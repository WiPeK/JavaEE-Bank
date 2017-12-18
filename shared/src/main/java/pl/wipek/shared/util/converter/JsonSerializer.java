package pl.wipek.shared.util.converter;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Set;

public class JsonSerializer {

    private static Marshaller marshaller;

    private static <E> void createMarshmaller(Class<E> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    public static <E> String convertSet(Set<E> set, Class<E> clazz) throws JAXBException {
        StringWriter stringWriter = new StringWriter();
        createMarshmaller(clazz);
        marshaller.marshal(set, stringWriter);
        return stringWriter.toString();
    }

    public static <E> String convertObject(E obj, Class<E> clazz) throws JAXBException {
        StringWriter stringWriter = new StringWriter();
        createMarshmaller(clazz);
        marshaller.marshal(obj, stringWriter);
        return stringWriter.toString();
    }
}
