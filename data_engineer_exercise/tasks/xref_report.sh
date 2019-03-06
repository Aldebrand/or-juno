#!/usr/bin/env bash
curl --url 'smtps://smtp.gmail.com:465' --ssl-reqd \
  --mail-from 'runtibi@gmail.com' --mail-rcpt 'archybald9@gmail.com' \
  --upload-file D:\\Workspace\\Java\\QueryExecutor\\test.csv --user 'runtibi@gmail.com:' --insecure
