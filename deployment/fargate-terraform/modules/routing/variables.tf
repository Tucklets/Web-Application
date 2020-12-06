// Routing variables
variable "domain_name" {
  description = "Name of the service's domain"
  default     = "tucklets.net"
}

variable "record_ttl" {
  description = "TTL (Time-to-live) for a DNS record"
  default     = 60
}

variable "lb_zone_id" {
  description = "Zone id for the load balancer"
}

variable "lb_dns_name" {
  description = "Load balancer's DNS name"
  type        = string
}