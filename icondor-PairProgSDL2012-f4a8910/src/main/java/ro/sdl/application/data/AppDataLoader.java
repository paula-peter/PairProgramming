package ro.sdl.application.data;


import ro.sdl.domain.Project;
import ro.sdl.domain.Role;
import ro.sdl.domain.State;
import ro.sdl.domain.User;
import ro.sdl.repository.ProjectRepository;
import ro.sdl.repository.ProjectRepositoryMemoryImpl;
import ro.sdl.repository.RepositoryException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AppDataLoader {

    private AppDataLoader() {
    }

    public static List<User> users = new ArrayList<User>();
    public static List<Project> projects = new ArrayList<Project>();

    static {
        loadDataFromXML();
    }

    private boolean loadDataFromMemory() {
        boolean ok = true;
        User user1 = new User(1, "Ion", Role.QA, State.MID);
        User user2 = new User(2, "Ana", Role.DEV, State.JUNIOR);
        User user3 = new User(3, "Radu", Role.DEV, State.SENIOR);
        User user4 = new User(4, "Gabi", Role.DEV, State.SENIOR);
        User user5 = new User(5, "Paula", Role.QA, State.SENIOR);
        User user6 = new User(6, "Alteia", Role.QA, State.MID);
        Project project1 = new Project(1, "BigBird");
        Project project2 = new Project(2, "BeGlobal");

        List<User> project1List = new ArrayList<User>();
        project1List.add(user1);
        project1List.add(user2);
        project1List.add(user3);
        List<User> project2List = new ArrayList<User>();
        project2List.add(user4);
        project2List.add(user5);
        project2List.add(user6);
        project1.setUsers(project1List);
        project2.setUsers(project2List);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        projects.add(project1);
        projects.add(project2);
        return ok;
    }

    private static boolean loadDataFromXML() {
        boolean ok = false;
        ok = loadProjectsFromXML();
        ok = loadUsersFromXML();
        return ok;
    }

    private static boolean loadUsersFromXML() {

        boolean ok = true;
        String USER = "user";
        String ID = "id";
        String NAME = "name";
        String ROLE = "role";
        String STATE = "state";
        String PROJECT_ID = "project_id";

        //List<User> items = new ArrayList<Item>();
        try {
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            String usersSourceFile = "files/xml/users.xml";
            InputStream in = new FileInputStream(usersSourceFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // Read the XML document
            User item = null;
            ProjectRepository projectRepository = new ProjectRepositoryMemoryImpl();

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a item element we create a new item
                    if (startElement.getName().getLocalPart() == (USER)) {
                        item = new User();
                        // We read the attributes from this tag and add the date
                        // attribute to our object
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals(ID)) {
                                item.setId(Integer.valueOf(attribute.getValue()));
                            }

                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(NAME)) {
                            event = eventReader.nextEvent();
                            item.setName(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(ROLE)) {
                        event = eventReader.nextEvent();
                        item.setRole(Role.valueOf(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(STATE)) {
                        event = eventReader.nextEvent();
                        item.setState(State.valueOf(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PROJECT_ID)) {
                        event = eventReader.nextEvent();
                        String idprj = event.asCharacters().getData();
                        Project project = projectRepository.load(Integer.valueOf(idprj));
                        if (project != null) {
                            item.setProject(project);
                            if (project.getUsers() == null) {
                                project.setUsers(new ArrayList());
                            }
                            project.getUsers().add(item);
                        } else {
                            System.out.println("Invalid project ID");
                        }
                        continue;
                    }


                }
                // If we reach the end of an item element we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart() == (USER)) {
                        users.add(item);
                    }
                }
            }
        } catch (RepositoryException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return ok;
    }

    private static boolean loadProjectsFromXML() {

        boolean ok = true;
        String PROJECT = "project";
        String ID = "id";
        String DESCRIPTION = "description";


        //List<User> items = new ArrayList<Item>();
        try {
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            String usersSourceFile = "files/xml/projects.xml";
            InputStream in = new FileInputStream(usersSourceFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // Read the XML document
            Project item = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a item element we create a new item
                    if (startElement.getName().getLocalPart() == (PROJECT)) {
                        item = new Project();
                        // We read the attributes from this tag and add the date
                        // attribute to our object
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals(ID)) {
                                item.setId(Integer.valueOf(attribute.getValue()));
                            }

                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(DESCRIPTION)) {
                            event = eventReader.nextEvent();
                            item.setDescription(event.asCharacters().getData());
                            continue;
                        }
                    }
                }
                // If we reach the end of an item element we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart() == (PROJECT)) {
                        projects.add(item);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return ok;
    }

    public static void hello() {
    }

}
