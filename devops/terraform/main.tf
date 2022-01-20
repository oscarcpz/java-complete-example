terraform {
  required_providers {
    docker = {
      source = "kreuzwerker/docker"
    }

  }
}

provider "docker" {
  # docker daemon needs to be running
  host = "unix:///var/run/docker.sock"
}
