Neosave

Steps:

    1. Download and Install Java IDE (Eclipse, Spring Tool Suite, Intellij, etc.)
    2. Clone the project and run "mvn clean install -Dskiptests" for building the project.
    3. Run the NsApplication.java file present inside src/main/java/neosave/ns package.
    4. Application will be Up and Running at base url : localhost:8080 .
    5. Database is deployed on AWS, following are the credentials for connecting the db on local : 
          Host : database-neosave.coowy3jfw39y.ap-south-1.rds.amazonaws.com
          Port : 5432
          Database : postgres
          Username : postgres
          Password : fLlBB1sndLA
          RDBMS Type : PostgreSQL
          Table : Users

API Description:

    1. API Name : Create User
       API Type : POST
       Path : BASE URL + /user/create
       Request Params : 
                name : Sarthak
                email : sarthak@gmail.com
                addressPincode : 757043

Request Sample
![image](https://user-images.githubusercontent.com/40289413/181770213-c3ae089d-c2ec-417d-995d-78422be7c695.png)


Response Sample
![image](https://user-images.githubusercontent.com/40289413/181768908-647f4a13-58b6-4058-a2b0-d04252f28b6e.png)

    2. API Name : Fetch User
       API Type : GET
       Path BASE_URL + /user/{userId}

Request Sample
![image](https://user-images.githubusercontent.com/40289413/181770039-935ef906-90e3-4815-98aa-f1bd37e3f545.png)

Response Sample
![image](https://user-images.githubusercontent.com/40289413/181770093-c921efb8-2688-4079-ac3f-085a858b0d56.png)
