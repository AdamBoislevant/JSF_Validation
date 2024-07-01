package com.coreservlets;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("projectBean")
@SessionScoped
public class ProjectBean implements Serializable {

    private final List<Project> projects;
    private Project selectedProject;
    private Project editedProject;
    private List<String> imageList;

    // Constructor
    public ProjectBean() {
        projects = new ArrayList<>();
        // Initialize projects (you can add more or modify as needed)
        projects.add(new Project("SSOToken", "SSOToken, Angular, Spring",
                "Generali VIE", "resources/images/generali.jpg", "One of the biggest project I have been involved in is my internship In Generali VIE. My project was to create a web application for the developpers use.To put this into context, some missions require access to the SSOGF access token, which is unique to each user and connection environment, and changes with each new connection. In addition, Generali has several development environments, depending on the use, needs and status of each user. There are 4 of them: production, pre-production, acceptance and integration. ",
                "My mission was to create a GUI enabling users to choose the environment they wished to connect to from the 4 available on the company intranet, and redirect them to the corresponding connection page if they were not yet connected. Once logged in, the user was redirected to a third page. The HMI would then retrieve the connection token (IdToken) and display it on this third page. Moreover, the SSO system is used to ensure greater IT security for all employees connected to their Generali accounts. It therefore uses security protocols such as OAuth 2.0. You can find more informations about the functionning of OAuth 2.0 on their site(<a class='link' href='https://oauth.net/2/'>https://oauth.net/2/</a>) Here you can find how a SSO token works: ", "resources/images/auth0.png", "resources/images/css.jpg"));

        projects.add(new Project("Hackathon", "Hackathon, Python, BI",
                "Colas Digital Solutions", "resources/images/colas.png", "Last year I had the chance to work on a hackathon in collaboration with Colas Digital solutions and my engineering school: CY Tech. This event took place in my school's site from 18th to 20th of october 2023, it was composed of 12 teams of 5 students so 60 students in total from any year. The theme of this hackathon was the AI-based image recognition and feature detection, the subject was divided in 3 parts: Data preparation, Data Science and Data visualisation (PBI).",
                "My mission was to train a computer vision model to detect degradations on airport runways. Colas has supplied a labeled dataset, consisting of 800 training images and 300 additional images, all in a 512x512 resolution, to facilitate this task. The model was evaluated on a separate test dataset. The dataset was already prepared by other member of the team you can see right next: ", "resources/images/hackathon.jpg", "resources/images/hackathonFace.jpg"));

        loadImages();
    }

    private void loadImages() {
        File folder = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images"));
        File[] listOfFiles = folder.listFiles();
        imageList = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    imageList.add("resources/images/" + file.getName());
                }
            }
        }
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void init() {
        // Method to initialize data based on URL parameters (if needed)
        // Request the title from the URL
        String titleParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("title");
        if (titleParam != null) {
            // Find the project corresponding to the title
            selectedProject = findProjectByTitle(titleParam);
            //Change the editedProject's value to get the correct edition form
            editedProject = selectedProject;
        } else {
            // Default to the first project if no title parameter is provided
            selectedProject = new Project();
        }
    }

    // Getters and setters
    public List<Project> getProjects() {
        return projects;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public Project getEditedProject() { return editedProject; }

    // Save the edited project's data to replace the actual project's data
    public void saveChanges() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        // Validate and save changes to the selected project
        if (validateProjectData(editedProject)) {
            setSelectedProject(editedProject);
            // Redirect to the project page
            externalContext.redirect("./project.xhtml?title=" + editedProject.getTitle());
        } else {
            // Reload the edit-project page
            externalContext.redirect("./edit-project.xhtml");
        }
    }

    private boolean validateProjectData(Project project) {
        // Verify if any data is missing or invalid
        return project.getTitle() != null && !project.getTitle().isEmpty()
                && project.getKeywords() != null && !project.getKeywords().isEmpty()
                && project.getCompanyName() != null && !project.getCompanyName().isEmpty()
                && project.getSummary() != null && !project.getSummary().isEmpty()
                && project.getDescription() != null && !project.getDescription().isEmpty();
    }

    public void cancelChanges() throws IOException {
        // Cancel the changes done to the edition form
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        // Reload the edit-project page
        externalContext.redirect("./project.xhtml?title=" + selectedProject.getTitle());
    }

    public void redirectToEditProject() throws IOException {
        // Redirect to the edit-project page
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.redirect("edit-project.xhtml");
    }

    private Project findProjectByTitle(String title) {
        // Method to find a project by its title
        return projects.stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}
