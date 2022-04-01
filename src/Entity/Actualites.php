<?php

namespace App\Entity;

use App\Repository\ActualitesRepository;
use Doctrine\ORM\Mapping as ORM;
use DeepCopy\TypeFilter\Date;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @ORM\Entity(repositoryClass=ActualitesRepository::class)
 */
class Actualites
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     * @Groups("post:read")
     */
    private $image;

    /**
     * @ORM\Column(type="text")
     * @Assert\NotBlank
     * @Groups("post:read")
     */

    private $Description;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     * @Groups("post:read")
     */
    private $nom;



    public function makeimagefile()
    {
        return  $file=new File($this->image);

    }


    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * @return mixed
     */




    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    

    public function __toString()
    {
        return(string)$this->getNom();
    }


}
