// MessagesFragment.java
package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {

    private ListView messageListView;
    private EditText messageEditText;
    private Button sendButton;
    private List<Message> messages;
    private MessageAdapter messageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        messageListView = view.findViewById(R.id.messageListView);
        messageEditText = view.findViewById(R.id.messageEditText);
        sendButton = view.findViewById(R.id.sendButton);

        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(requireContext(), messages);
        messageListView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(v -> sendMessage());

        // Ajoutez quelques messages de test
        messages.add(new Message("Bonjour je suis interesser par votre article", false));
        messages.add(new Message("il est disponible a partir du 15", true));
        messages.add(new Message("Parfait !", true));

        messageAdapter.notifyDataSetChanged();

        return view;
    }

    private void sendMessage() {
        String messageContent = messageEditText.getText().toString().trim();
        if (!messageContent.isEmpty()) {
            messages.add(new Message(messageContent, true));
            // Ajoutez ici la logique pour envoyer le message au vendeur.

            // Effacez le champ de texte après l'envoi du message
            messageEditText.getText().clear();

            // Mise à jour de l'adaptateur pour refléter le nouveau message
            messageAdapter.notifyDataSetChanged();
        }
    }
}
