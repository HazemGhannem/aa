<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220228191902 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE actualites ADD categorie_id INT DEFAULT NULL, CHANGE image image VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE actualites ADD CONSTRAINT FK_75315B6DBCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie (id)');
        $this->addSql('CREATE INDEX IDX_75315B6DBCF5E72D ON actualites (categorie_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE actualites DROP FOREIGN KEY FK_75315B6DBCF5E72D');
        $this->addSql('DROP INDEX IDX_75315B6DBCF5E72D ON actualites');
        $this->addSql('ALTER TABLE actualites DROP categorie_id, CHANGE image image LONGTEXT NOT NULL');
    }
}
