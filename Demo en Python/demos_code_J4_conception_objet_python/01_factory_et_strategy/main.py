from abc import ABC, abstractmethod


class Sender(ABC):
    @abstractmethod
    def send(self, message: str) -> None:
        pass


class EmailSender(Sender):
    def send(self, message: str) -> None:
        print(f"EMAIL -> {message}")


class SmsSender(Sender):
    def send(self, message: str) -> None:
        print(f"SMS   -> {message}")


class PushSender(Sender):
    def send(self, message: str) -> None:
        print(f"PUSH  -> {message}")


class SenderFactory:
    @staticmethod
    def create(sender_type: str) -> Sender:
        senders = {
            "email": EmailSender,
            "sms": SmsSender,
            "push": PushSender,
        }
        try:
            return senders[sender_type.lower()]()
        except KeyError as exc:
            raise ValueError(f"Canal inconnu: {sender_type}") from exc


class NotificationService:
    def __init__(self, sender: Sender) -> None:
        self._sender = sender

    def notify(self, message: str) -> None:
        self._sender.send(message)


def main() -> None:
    print("=== Factory choisit, Strategy exécute ===")
    for sender_type in ("email", "sms", "push"):
        sender = SenderFactory.create(sender_type)
        NotificationService(sender).notify("Commande #42 confirmée")

    print("\nÀ commenter : NotificationService ne connaît aucune classe concrète.")


if __name__ == "__main__":
    main()
