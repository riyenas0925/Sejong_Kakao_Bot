package dev.riyenas.chatbot.web.skill.common;

public enum QuickReplyEnum {
    MESSAGE(){
        @Override
        public QuickReply action(String label, String actionResponse) {
            return new QuickReply(label, "message", actionResponse, null, null);
        }
    },BLOCK(){
        @Override
        public QuickReply action(String label, String actionResponse) {
            return new QuickReply(label, "block", null, actionResponse, null);
        }
    };

    abstract public QuickReply action(String label, String actionResponse);
}
