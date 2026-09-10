def total(initial: int) -> int:
    if initial < 0:
        raise ValueError("montant négatif")
    return initial * 90 // 100 if initial >= 100 else initial
