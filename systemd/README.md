# System D

To run node services under linux as a systemd service we need to setup some service files under the `/lib/systemd/system` folder.

Copy the quark.service, quark_api.service, quark_dev.service and quark_dev_api.service to this folder and run:

```bash
sudo systemctl daemon-reload
```

Enable and start the services as required however its recommended to do this for all of them.

The NGINX configuration will tie into these services in order to host the API as well as the file servers.