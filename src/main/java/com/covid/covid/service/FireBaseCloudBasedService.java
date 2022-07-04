package com.covid.covid.service;

import com.covid.covid.dto.LocationDetailDTO;
import com.covid.covid.entity.LocationDetail;
import com.covid.covid.repository.ContactTracingDetailRepository;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FireBaseCloudBasedService {
    private final Logger logger = LoggerFactory.getLogger(FireBaseCloudBasedService.class);

    @Autowired
    private ContactTracingDetailRepository contactTracingDetailRepository;


    @Value("${firebase.json.path}")
    private String fireBaseConfigJSONPath;

    @PostConstruct
    public void initFirestore() {

        try {

            FileInputStream serviceAccount =
                    new FileInputStream(new ClassPathResource(fireBaseConfigJSONPath).getFile());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            logger.error("Exception in FireBaseService ==> initFirestore: {}", e.getMessage());
        }
    }

    public String saveUpdateLocationDetails(LocationDetailDTO location) {
        String updatedTime = null;
        try {
            Firestore firestoreInstance = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> apiFuture = firestoreInstance.collection("location").document(location.getId().toString()).set(location);
            updatedTime =  apiFuture.get().getUpdateTime().toString();
        } catch (Exception e) {
            logger.error("Exception in FireBaseService ==> saveLocationDetails: Location Detail Id: {}, Error: {}", location.getId(), e.getMessage());
        }
        return updatedTime;
    }


}
