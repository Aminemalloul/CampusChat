package demo.campuschat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import demo.campuschat.model.ChatSummary;
import demo.campuschat.model.Message;
import demo.campuschat.model.User;

public class DataSeeder {

//    public static void seedUsers(DatabaseReference databaseReference) {
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.exists()) {
//                    // Data does not exist, proceed with seeding
//                    List<User> users = new ArrayList<>();
//                    // Add some test users
//                    users.add(new User("yMzSSdkEOZVQvETXFRwUs3f0mAf1", "user1", "User1@example.com"));
//                    users.add(new User("rKRIkZW1EHQny9npQg3uHvvA0cC3", "user2", "User2@example.com"));
//                    // ...
//
//                    for (User user : users) {
//                        databaseReference.child(user.getUserId()).setValue(user);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle possible errors
//            }
//        });
//    }

    public static void seedMessages(DatabaseReference databaseReference) {

        List<Message> messages = new ArrayList<>();
        // Add some test messages
        messages.add(new Message("JenIABYjFFfs51RPxEdhiQqhXb42", "wkSU1jlFo7eriCoUqrmiehoefQo1", "Hello User2!", System.currentTimeMillis()));
        messages.add(new Message("wkSU1jlFo7eriCoUqrmiehoefQo1", "JenIABYjFFfs51RPxEdhiQqhXb42", "Hi User1!", System.currentTimeMillis()));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    // Data does not exist, proceed with seeding
                    for (Message message : messages) {
                        databaseReference.push().setValue(message);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }


    public static void seedChatSummaries(DatabaseReference databaseReference) {
        List<ChatSummary> chatSummaries = new ArrayList<>();

        // Here we're creating two chat summaries to correspond with the two messages above
        ChatSummary chatSummary1 = new ChatSummary(
                "wkSU1jlFo7eriCoUqrmiehoefQo1",
                "User2",
                "Hi User1!",
                System.currentTimeMillis()
        );

        ChatSummary chatSummary2 = new ChatSummary(
                "JenIABYjFFfs51RPxEdhiQqhXb42",
                "User1",
                "Hi User1!",
                System.currentTimeMillis() // The timestamp for this last message
        );


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    // Data does not exist, proceed with seeding

                    // For User1's chat summaries, we show the summary of the chat with User2
                    databaseReference.child("JenIABYjFFfs51RPxEdhiQqhXb42").child("wkSU1jlFo7eriCoUqrmiehoefQo1").setValue(chatSummary1);

                    // For User2's chat summaries, we show the summary of the chat with User1
                    databaseReference.child("wkSU1jlFo7eriCoUqrmiehoefQo1").child("JenIABYjFFfs51RPxEdhiQqhXb42").setValue(chatSummary2);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
            }
        });

    }
}

