import hashlib


class OrderRepository:
    """Détail technique interne au package orders."""

    def insert(self, customer: str) -> str:
        digest = hashlib.sha1(customer.encode("utf-8")).hexdigest()[:8]
        return f"O-{int(digest, 16)}"
