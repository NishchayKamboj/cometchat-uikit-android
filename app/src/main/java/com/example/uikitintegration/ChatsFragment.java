package com.example.uikitintegration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cometchat.chat.constants.CometChatConstants;
import com.cometchat.chat.models.Conversation;
import com.cometchat.chat.models.Group;
import com.cometchat.chat.models.User;
import com.cometchat.chatuikit.conversations.CometChatConversations;

public class ChatsFragment extends Fragment {

    private CometChatConversations conversationsView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        // Initialize views with the inflated view
        initView(view);
        setListeners();

        return view;
    }

    private void initView(View view) {
        conversationsView = view.findViewById(R.id.cometChatConversations);
    }

    private void setListeners() {
        if (conversationsView != null) {
            conversationsView.setOnItemClick((view, position, conversation) -> {
                startMessageActivity(conversation);
            });
        }
    }

    private void startMessageActivity(Conversation conversation) {
        Intent intent = new Intent(requireContext(), MessageActivity.class);

        if (CometChatConstants.CONVERSATION_TYPE_GROUP.equals(conversation.getConversationType())) {
            Group group = (Group) conversation.getConversationWith();
            intent.putExtra("guid", group.getGuid());
        } else {
            User user = (User) conversation.getConversationWith();
            intent.putExtra("uid", user.getUid());
        }

        startActivity(intent);
    }
}
