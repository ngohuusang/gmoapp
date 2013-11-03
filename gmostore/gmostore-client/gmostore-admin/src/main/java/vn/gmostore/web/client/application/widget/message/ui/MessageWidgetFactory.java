package vn.gmostore.web.client.application.widget.message.ui;

import vn.gmostore.web.client.application.widget.message.Message;

public interface MessageWidgetFactory {
    public MessageWidget createMessage(Message message);
}
