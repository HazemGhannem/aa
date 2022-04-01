<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Form\CategorieType;
use Doctrine\ORM\EntityManager;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Component\HttpFoundation\Request;

class CategorieController extends AbstractController
{
    /**
     * @Route("/categorie", name="categorie")
     */
    public function index(): Response
    {
        return $this->render('categorie/index.html.twig', [
            'controller_name' => 'CategorieController',
        ]);
    }
    /**
     * @Route("/list",name="listcategorie")
     */
   public function list()
   {
       $cat=$this->getDoctrine()->getRepository(Categorie::class)->findAll();
       return $this->render("categorie/index.html.twig",array('categorie'=>$cat));

   }
    /**
     * @Route("/add", name="addcategorie")
     */
    function addcategegorie(Request $request)
    {
        $categorie = new Categorie();
        $formcateg = $this->createForm(CategorieType::class, $categorie);
        $formcateg->handleRequest($request);
        if ($formcateg->isSubmitted() && $formcateg->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($categorie);
            $em->flush();
            return $this->redirectToRoute("listcategorie");
        }
        return $this->render("categorie/categorie.html.twig", array('form' => $formcateg->createView()));
    }

    /**
     * @Route("/update/{id}",name="updateCategorie")
     */
    public function updatecategorie(Request $request, $id)
    {
        $categorie = $this->getDoctrine()->getRepository(Categorie::class)->find($id);
        $formcateg = $this->createForm(CategorieType::class, $categorie);
        $formcateg->handleRequest($request);
        if ($formcateg->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("listcategorie");

        }
        return $this->render("categorie/categorie.html.twig", array("form" => $formcateg->createView()));
    }
    /**
     * @Route("/delete/{id}",name="deleteCategorie")
     */
    public function deletecategorie($id)
    {
        $categorie = $this->getDoctrine()->getRepository(Categorie::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($categorie);
        $em->flush();
        return $this->redirectToRoute("listcategorie");

    }
}