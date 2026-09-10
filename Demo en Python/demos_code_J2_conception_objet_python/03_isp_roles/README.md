# Démo 03 — ISP : séparer selon les clients

**Slides : 16 à 20.** Durée : 10 à 12 min.

### Avant
```bash
python demo_isp_avant.py
```

Montrez le `NotImplementedError`. L’objet est forcé de promettre des capacités qu’il ne possède pas.

### Après
```bash
python demo_isp.py
```

`PaymentScreen` ne voit que `PaymentReader`, tandis que `RefundUseCase` ne voit que ce dont il a besoin. L’implémentation concrète peut implémenter plusieurs rôles.

À marteler : **ISP ne signifie pas « une méthode par interface »**. Le bon découpage suit les raisons de changer et les consommateurs réels.
