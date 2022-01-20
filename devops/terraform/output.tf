output "port_jenkins" {
  value = docker_container.jenkins.ports
}

output "port_sonarqube" {
  value = docker_container.sonarqube.ports
}

output "ip_address_jenkins" {
  value = docker_container.jenkins.ip_address
}

output "ip_address_sonarqube" {
  value = docker_container.sonarqube.ip_address
}
