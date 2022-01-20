resource "docker_image" "jenkins" {
  name         = "jenkins/jenkins:lts-jdk11"
  keep_locally = false
}

resource "docker_container" "jenkins" {
  image   = docker_image.jenkins.latest
  name    = "jenkins"
  restart = "always"

  ports {
    internal = 8080
    external = 8080
  }

  ports {
    internal = 50000
    external = 50000
  }

  volumes {
    container_path = "/var/jenkins_home"
    host_path = var.jenkins_home
  }

}
