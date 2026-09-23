import React, { useState } from 'react';
import { Modal, Button, Alert } from 'react-bootstrap';

const ConfirmationModal = ({ show, onClose, onConfirm }) => (
  <Modal show={show} onHide={onClose}>
    <Modal.Header closeButton>
      <Modal.Title>Confirmation</Modal.Title>
    </Modal.Header>
    <Modal.Body>Êtes-vous sûr de vouloir supprimer cet élément ?</Modal.Body>
    <Modal.Footer>
      <Button variant="secondary" onClick={onClose}>
        Non
      </Button>
      <Button variant="primary" onClick={onConfirm}>
        Oui
      </Button>
    </Modal.Footer>
  </Modal>
);

const SuccessAlert = ({ message, show, onClose }) => (
  <Modal show={show} onHide={onClose}>
    <Modal.Header closeButton>
      <Modal.Title>Succès</Modal.Title>
    </Modal.Header>
    <Modal.Body>{message}</Modal.Body>
    <Modal.Footer>
      <Button variant="primary" onClick={onClose}>
        OK
      </Button>
    </Modal.Footer>
  </Modal>
);

export default ConfirmationModal; SuccessAlert;
