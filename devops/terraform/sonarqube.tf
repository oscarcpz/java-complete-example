resource "docker_image" "sonarqube" {
  name         = "sonarqube:9-community"
  keep_locally = false
}

resource "docker_container" "sonarqube" {
  image   = docker_image.sonarqube.latest
  name    = "sonarqube"
  restart = "always"

  ports {
    internal = 9000
    external = 9000
  }
}