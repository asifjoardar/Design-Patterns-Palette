package org.asif.workflow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentTest {

    @Test
    void aNewDocumentStartsAsADraft() {
        Document doc = new Document("Title", Role.AUTHOR);

        assertEquals("Draft", doc.currentState());
    }

    @Test
    void publishingADraftSendsItToModeration() {
        Document doc = new Document("Title", Role.AUTHOR);

        doc.publish();

        assertEquals("Moderation", doc.currentState());
    }

    @Test
    void anAuthorCannotApproveTheirOwnDocument() {
        Document doc = new Document("Title", Role.AUTHOR);
        doc.publish();   // -> Moderation

        doc.publish();   // author tries to approve

        assertEquals("Moderation", doc.currentState());
    }

    @Test
    void anAdminApprovesAndPublishesADocumentInModeration() {
        Document doc = new Document("Title", Role.AUTHOR);
        doc.publish();   // -> Moderation
        doc.actAs(Role.ADMIN);

        doc.publish();   // admin approves

        assertEquals("Published", doc.currentState());
    }

    @Test
    void publishingAnAlreadyPublishedDocumentIsANoOp() {
        Document doc = new Document("Title", Role.ADMIN);
        doc.publish();   // -> Moderation
        doc.publish();   // admin approves -> Published

        doc.publish();   // already published

        assertEquals("Published", doc.currentState());
    }

    @Test
    void fullLifecycleMovesDraftToModerationToPublished() {
        Document doc = new Document("Title", Role.AUTHOR);

        doc.publish();                       // Draft -> Moderation
        doc.actAs(Role.ADMIN);
        doc.publish();                       // Moderation -> Published

        assertEquals("Published", doc.currentState());
    }
}
