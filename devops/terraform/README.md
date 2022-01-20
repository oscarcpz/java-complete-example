# Terraform

## Install Terraform

## Commands

* `$ terraform init` - Prepare your working directory for other commands
* `$ terraform fmt` - Reformat your configuration in the standard style
* `$ terraform validate` - Check whether the configuration is valid
* `$ terraform plan` - Show changes required by the current configuration
* `$ terraform apply` - Create or update infrastructure. By default **jenkins_home** = /opt/jenkins. Defined in "variables.tf"
  * `$ terraform apply -var="jenkins_home=/<path_to_local>/jenkins_home"`
* `$ terraform destroy` - Destroy previously-created infrastructure
* `$ docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword` - get jenkins initial password

## Files

* terraform.tfstate - store state about your managed infrastructure and configuration. If you lost this file you will lost the connection to real world. Please, keep this file safe.
* xxx.tf - infrastructure as code. Each file store the definition of a resource. You could write all resources in only one file but this could be a mess.
