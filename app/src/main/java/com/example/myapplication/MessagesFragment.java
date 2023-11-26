package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesFragment extends Fragment {

    private ListView userListView;
    private EditText messageEditText;
    private Button sendButton;
    private List<String> usernames;
    private ArrayAdapter<String> userAdapter;
    private List<Message> messages;
    private MessageAdapter messageAdapter;
    private Map<String, List<Message>> userMessagesMap; // Mapping des messages par utilisateur
    private String selectedUser; // Utilisateur sélectionné

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        userListView = view.findViewById(R.id.userListView);
        messageEditText = view.findViewById(R.id.messageEditText);
        sendButton = view.findViewById(R.id.sendButton);

        usernames = new ArrayList<>();
        usernames.add("User1");
        usernames.add("User2");
        // Ajoutez d'autres utilisateurs selon vos besoins

        userAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, usernames);
        userListView.setAdapter(userAdapter);

        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(requireContext(), messages);
        ListView messageListView = view.findViewById(R.id.messageListView);
        messageListView.setAdapter(messageAdapter);

        // Initialiser le mapping des messages par utilisateur
        userMessagesMap = new HashMap<>();
        // Ajoutez des messages de test pour chaque utilisateur
        userMessagesMap.put("User1", getSampleMessages("User1"));
        userMessagesMap.put("User2", getSampleMessages("User2"));
        // Ajoutez d'autres utilisateurs selon vos besoins

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // L'utilisateur a cliqué sur un utilisateur, chargez les messages de cet utilisateur
                selectedUser = usernames.get(position);
                loadMessagesForUser();
            }
        });

        sendButton.setOnClickListener(v -> sendMessage());

        return view;
    }

    private List<Message> getSampleMessages(String user) {
        List<Message> sampleMessages = new ArrayList<>();
        // Ajoutez des messages de test pour l'utilisateur spécifié
        sampleMessages.add(new Message("Bonjour, comment ça va ?", false));
        sampleMessages.add(new Message("Je suis intéressé par votre produit.", true));
        // Ajoutez d'autres messages selon vos besoins
        return sampleMessages;
    }

    private void loadMessagesForUser() {
        if (selectedUser != null && userMessagesMap.containsKey(selectedUser)) {
            // Chargez les messages de l'utilisateur sélectionné
            List<Message> userMessages = userMessagesMap.get(selectedUser);

            // Mettez à jour la liste des messages
            messages.clear();
            messages.addAll(userMessages);

            // Mettez à jour l'adaptateur pour refléter les nouveaux messages
            messageAdapter.notifyDataSetChanged();
        }
    }

    private void sendMessage() {
        String messageContent = messageEditText.getText().toString().trim();
        if (!messageContent.isEmpty() && selectedUser != null) {
            // Ajoutez le message à la liste des messages de l'utilisateur sélectionné
            List<Message> userMessages = userMessagesMap.get(selectedUser);
            userMessages.add(new Message(messageContent, true));

            // Effacez le champ de texte après l'envoi du message
            messageEditText.getText().clear();

            // Mettez à jour l'adaptateur pour refléter le nouveau message
            messageAdapter.notifyDataSetChanged();
        }
    }
}
