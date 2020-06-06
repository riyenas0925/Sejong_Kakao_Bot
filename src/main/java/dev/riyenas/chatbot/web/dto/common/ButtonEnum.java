package dev.riyenas.chatbot.web.dto.common;

public enum ButtonEnum{
    WEBLINK{
        @Override
        public Button action(String label, String actionResponse) {
            return new Button(label, "webLink", actionResponse, null, null, null);
        }
    }, MESSAGE{
        @Override
        public Button action(String label, String actionResponse) {
            return new Button(label, "message", null, actionResponse, null, null);
        }
    }, PHONE{
        @Override
        public Button action(String label, String actionResponse) {
            return new Button(label, "phone", null, null, actionResponse, null);
        }
    }, BLOCK{
        @Override
        public Button action(String label, String actionResponse) {
            return new Button(label, "phone", null, null, null, actionResponse);
        }
    }, SHARE{
        @Override
        public Button action(String label, String actionResponse) {
            return null;
        }
    }, OPERATOR{
        @Override
        public Button action(String label, String actionResponse) {
            return null;
        }
    };

    abstract public Button action(String label, String actionResponse);
}
