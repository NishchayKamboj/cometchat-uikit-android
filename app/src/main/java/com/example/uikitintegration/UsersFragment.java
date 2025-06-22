package com.example.uikitintegration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cometchat.chat.models.User;
import com.cometchat.chatuikit.users.CometChatUsers;

public class UsersFragment extends Fragment {

    private CometChatUsers cometChatUsers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);

        cometChatUsers = view.findViewById(R.id.users);
        setListeners();

        return view;
    }

    private void setListeners() {
        if (cometChatUsers!= null) {
            cometChatUsers.setOnItemClick((view, position, conversation) -> {
                startMessageActivity(conversation);
            });
        }
    }


    private void startMessageActivity(User user) {
        Intent intent = new Intent(requireContext(), MessageActivity.class);
        intent.putExtra("uid", user.getUid());
        startActivity(intent);
    }
}
