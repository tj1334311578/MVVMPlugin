package com.at.mvvm.services

import com.intellij.openapi.project.Project
import com.at.mvvm.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
