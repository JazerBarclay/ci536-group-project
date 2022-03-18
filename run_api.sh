#!/bin/bash

pushd database
./pg_dev.sh
./pg_admin.sh
popd

pushd web/api
npm run dev
popd

echo "Exited"