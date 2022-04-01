<?php

namespace App\Controller;

use App\Entity\Actualites;
use App\Entity\Categorie;
use App\Form\SearchType;
use App\Repository\ActualitesRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class ActualitesfrontController extends AbstractController
{
    /**
     * @Route("/actualitesfront", name="actualitesfront")
     */
    public function index(): Response
    {
        return $this->render('actualitesfront/index.html.twig', [
            'controller_name' => 'ActualitesfrontController',
        ]);
    }
    /**
     * @Route("/listactf",name="listactualitesf")
     */
    public function list(Request $request,ActualitesRepository $T)
    {
        $act=$this->getDoctrine()->getRepository(Actualites::class)->findAll();
        $C=$this->getDoctrine()->getRepository(Categorie::class)->findAll();
        $formsearchI = $this->createForm(SearchType::class);
        $formsearchI->handleRequest($request);
        if ($formsearchI->isSubmitted()) {
            $nom = $formsearchI->getData();
            $TSearch = $T->search($nom);
           return $this->render("actualitesfront/search.html.twig",
                array("cat" => $TSearch, 'actualite' => $T)) ;
        }
        return $this->render("actualitesfront/index.html.twig",array('actualite'=>$act,'categories'=>$C,"formsearchI" => $formsearchI->createView()));


    }
    /**
     * @Route("/listactf/{id}",name="listactualitesf1")
     */
    public function listcat($id)
    {
        $cat=$this->getDoctrine()->getRepository(Categorie::class)->find($id);
        $act=$this->getDoctrine()->getRepository(Actualites::class)->get_by_categorie($cat);
        $C=$this->getDoctrine()->getRepository(Categorie::class)->findAll();
        return $this->render("actualitesfront/index.html.twig",array('actualite'=>$act,'categories'=>$C));

    }
    /**
     * @Route("/afficheact/{id}",name="listaffiche")
     */
    public function affiche($id)
    {
        $act=$this->getDoctrine()->getRepository(Actualites::class)->find($id);
        return $this->render("actualitesfront/afficher.html.twig",array('actualite'=>$act));

    }
}
