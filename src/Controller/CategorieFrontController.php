<?php

namespace App\Controller;

use App\Entity\Categorie;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CategorieFrontController extends AbstractController
{
    /**
     * @Route("/categorie/front", name="categorie_front")
     */
    public function index(): Response
    {
        return $this->render('categorie_front/index.html.twig', [
            'controller_name' => 'CategorieFrontController',
        ]);

    }
    /**
     * @Route("/listfront",name="listcatfront")
     */
    public function list()
    {
        $cat=$this->getDoctrine()->getRepository(Categorie::class)->findAll();
        return $this->render("categorie_front/index.html.twig",array('categorie'=>$cat));

    }
}
