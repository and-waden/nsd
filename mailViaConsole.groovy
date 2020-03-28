def theme = 'Прекращение приема заявок через почту.'
def body = 'Уважаемые коллеги, подать заявку в ИТ вы можете любым способом из инструкции. Функционал подачи заявок через почту отключен.<br>Инструкция доступна по ссылке: <a href="https://address.docx">Инструкция</a>'
def message = api.mail.sender.createMail()
message.addTo(null, 'author@mail.ru')
message.setFrom('ИТ-поддержка', 'it@mail.ru')
message.setSubject(theme)
message.setText(body)
message.contentType = 'text/html'
api.mail.sender.sendMail(message)