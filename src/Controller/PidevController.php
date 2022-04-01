<?php

namespace App\Controller;

use App\Entity\Actualites;
use App\Entity\Categorie;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class PidevController extends AbstractController
{
  /**
     * @Route("/azer/{id}",name="updateactua")
     */
    public function ee(Request $request,NormalizerInterface $normalizer,$id){
        $id = $request->get("id");
        $act= $this->getDoctrine()->getRepository(Actualites::class)->find($id);
        $act->setImage($request->get("image"));
        $act->setDescription($request->get("Description"));
        $act->setNom($request->get("nom"));
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $jsonContent = $normalizer->normalize($act,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));

    }
    /**
     * @Route("/updatee/{id}",name="updateactua")
     */
    public function updatecat(Request $request,NormalizerInterface $normalizer,$id){
        $id = $request->get("id");
        $cat= $this->getDoctrine()->getRepository(Categorie::class)->find($id);
        $cat->setSujet($request->get("sujet"));
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $jsonContent = $normalizer->normalize($cat,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));

    }

}
