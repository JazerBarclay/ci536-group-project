# Hosting settings

Domain: quark.rocks
HTTPS enabled: yes
API: api.quark.rocks
Site: www.quark.rocks

Dev Site: dev.quark.rocks
Dev API: dev.api.quark.rocks

Server will host all files under the /var/www/quark
Live deployment will be sent to /var/www/quark/live
Development work will be under /var/www/quark/dev

Github actions will send the branch commits to the right locations based on the script stored in .github/workflows

The deployment server will host the database, website, api and be configured using nginx, docker and nodejs.