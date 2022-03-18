#!/bin/bash

# Copy example to local file in api
# then install required files
pushd web/api
cp -n .env.example .env && echo "Created environment file for api"
npm i
popd

# Copy example to local file in www
# then install required files
pushd web/www
cp -n .env.example .env && echo "Created environment file for site"
npm i
popd

# Launch database and pgadmin
pushd database
./pg_dev.sh && echo "Launched postgres database" &&
./pg_admin.sh && echo "Launched admin panel for database"
popd

echo ""
echo "-----------------------------------------"
echo ""
echo "To launch the api, run:"
echo "  cd web/api && npm run dev"
echo "This is accessible via localhost:4000"
echo ""
echo "To run the site, run:"
echo "  cd web/www && npm run dev"
echo "This is accessible via localhost:3000"
echo ""
echo "-----------------------------------------"