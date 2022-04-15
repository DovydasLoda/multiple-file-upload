# multiple-file-upload

## Add multiple archiving methods

Multiple archiving methods can be added by implementing CompressedStorageService (two methods).
ZipStorageService.java is the example of Zip archiving method.

## Significant increase in request count

Increased requests count can be handled by various ways:
- Enable async operations;
- Increase request timeout
- Increase Thread Count
- Scale this program with Kubernetes and Docker;

## Allow 1GB max file size
Max allowed file size can be set in application.properties file.